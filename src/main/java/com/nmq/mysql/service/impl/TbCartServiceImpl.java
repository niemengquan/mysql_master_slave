package com.nmq.mysql.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nmq.mysql.entity.TbCart;
import com.nmq.mysql.mapper.TbCartMapper;
import com.nmq.mysql.service.ITbCartService;
import com.nmq.mysql.vo.ViewListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by niemengquan on 2017/11/9.
 */
@Service
public class TbCartServiceImpl  implements ITbCartService{
    @Autowired
    private TbCartMapper cartMapper;
    @Override
    @Transactional(readOnly = true)
    public ViewListResult getAll(int start,int pageSize) {
        Page<Object> localPage = PageHelper.startPage(start, pageSize);
        List<TbCart> tbCarts = cartMapper.selectAll();
        ViewListResult viewListResult = ViewListResult.build(localPage.getTotal(), localPage.getPageSize(), localPage.getPageNum(), tbCarts);
        return viewListResult;
    }

    @Override
    @Transactional(readOnly = true)
    public TbCart getTbCartById(Long id) {
        TbCart tbCart = this.cartMapper.selectByPrimaryKey(id);
        return tbCart;
    }

    @Override
    @Transactional
    public Integer deleteTbCartById(Long id) {
        return this.cartMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public Integer saveTbCart(TbCart cart) {
        //补全cart数据，主键id自增不用管
        Date date = new Date();
        cart.setCreated(date);
        cart.setUpdated(date);
        return this.cartMapper.insert(cart);
    }

    @Override
    public Integer updateTbCartById(TbCart cart) {
        if (StringUtils.isEmpty(cart.getId())){
            return 0;
        }
        //补全cart数据
        cart.setUpdated(new Date());
        return this.cartMapper.updateByPrimaryKey(cart);
    }
}
