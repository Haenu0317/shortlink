package com.haenu.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haenu.shortlink.project.common.convention.exception.ServiceException;
import com.haenu.shortlink.project.dao.entity.ShortLinkDO;
import com.haenu.shortlink.project.dao.mapper.LinkMapper;
import com.haenu.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.haenu.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.haenu.shortlink.project.service.ShortLinkService;
import com.haenu.shortlink.project.toolkit.HashUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * @author Haenu0317
 * @description 针对表【t_link】的数据库操作Service实现
 * @createDate 2023-12-20 10:57:56
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<LinkMapper, ShortLinkDO>
        implements ShortLinkService {

    private final RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter;

    /**
     * 创建短链接
     *
     * @param requestParam
     * @return
     */
    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        String generateSuffix = generateSuffix(requestParam);
        ShortLinkDO shortLinkDO = BeanUtil.copyProperties(requestParam, ShortLinkDO.class);
        String fullShortUrl = requestParam.getDomain() + "/" + generateSuffix;
        shortLinkDO.setShortUri(generateSuffix);
        shortLinkDO.setEnableStatus(0);
        shortLinkDO.setFullShortUrl(fullShortUrl);
        try {
            save(shortLinkDO);
        } catch (DuplicateKeyException e) {
            //todo 误判的如何处理?
            LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                    .eq(ShortLinkDO::getFullShortUrl, fullShortUrl);
            ShortLinkDO hasShortLinkDo = getOne(queryWrapper);
            if (hasShortLinkDo != null) {
                log.warn("创建短链接失败，短链接已存在，短链接：{}", fullShortUrl);
                throw new ServiceException("短链接生成重复");
            }

        }
        shortUriCreateCachePenetrationBloomFilter.add(generateSuffix);
        return ShortLinkCreateRespDTO
                .builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .build();
    }

    private String generateSuffix(ShortLinkCreateReqDTO requestParam) {
        int customGenerateCount = 0;
        String shortUri;
        while (true) {
            if (customGenerateCount > 10) {
                throw new ServiceException("短链接频繁生成，请稍后再试");
            }
            String originUrl = requestParam.getOriginUrl() + UUID.randomUUID().toString();
            shortUri = HashUtil.hashToBase62(originUrl);
            if (!shortUriCreateCachePenetrationBloomFilter.contains(shortUri)) {
                break;
            }
            customGenerateCount++;
        }

        return shortUri;
    }

}




