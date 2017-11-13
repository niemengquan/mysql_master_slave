package com.nmq.mysql.service;

import com.nmq.mysql.entity.TbCart;
import com.nmq.mysql.vo.ViewListResult;

import java.util.List;

/**
 * Created by niemengquan on 2017/11/9.
 */
public interface ITbCartService {
    int pageSize=10;
    /**
     * 获取所有的购物车数据（分页）
     * @param start 开始页数
     * @param pageSize 每页大小
     * @return
     */
    ViewListResult getAll(int start, int pageSize);

    /**
     * 根据Id获取
     * @param id
     * @return
     */
    TbCart getTbCartById(Long id);

    /**
     * 根据Id删除数据
     * @param id
     * @return
     */
    Integer deleteTbCartById(Long id);

    /**
     * 保存购物车
     * @param cart
     * @return
     */
    Integer saveTbCart(TbCart cart);

    /**
     * 更新数据
     * @param cart
     * @return
     */
    Integer updateTbCartById(TbCart cart);
}
