package com.nmq.mysql.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_cart")
public class TbCart {
    /**
     * 自增ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 商品ID
     */
    @Column(name = "item_id")
    private Long itemId;

    /**
     * 商品标题
     */
    @Column(name = "item_title")
    private String itemTitle;

    /**
     * 商品主图
     */
    @Column(name = "item_image")
    private String itemImage;

    /**
     * 商品价格，单位为：分
     */
    @Column(name = "item_price")
    private Long itemPrice;

    /**
     * 购买数量
     */
    private Integer num;

    private Date created;

    private Date updated;

    /**
     * 获取自增ID
     *
     * @return id - 自增ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增ID
     *
     * @param id 自增ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取商品ID
     *
     * @return item_id - 商品ID
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * 设置商品ID
     *
     * @param itemId 商品ID
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取商品标题
     *
     * @return item_title - 商品标题
     */
    public String getItemTitle() {
        return itemTitle;
    }

    /**
     * 设置商品标题
     *
     * @param itemTitle 商品标题
     */
    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    /**
     * 获取商品主图
     *
     * @return item_image - 商品主图
     */
    public String getItemImage() {
        return itemImage;
    }

    /**
     * 设置商品主图
     *
     * @param itemImage 商品主图
     */
    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    /**
     * 获取商品价格，单位为：分
     *
     * @return item_price - 商品价格，单位为：分
     */
    public Long getItemPrice() {
        return itemPrice;
    }

    /**
     * 设置商品价格，单位为：分
     *
     * @param itemPrice 商品价格，单位为：分
     */
    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * 获取购买数量
     *
     * @return num - 购买数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置购买数量
     *
     * @param num 购买数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * @return created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * @param updated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}