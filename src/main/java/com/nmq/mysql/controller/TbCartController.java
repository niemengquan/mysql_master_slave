package com.nmq.mysql.controller;

import com.nmq.mysql.entity.TbCart;
import com.nmq.mysql.service.ITbCartService;
import com.nmq.mysql.util.JsonUtils;
import com.nmq.mysql.vo.ViewListResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by niemengquan on 2017/11/9.
 */
@Api(value = "tbCartController",description = "购物车的Restful接口操作")
@RestController
public class TbCartController {
    @Autowired
    private ITbCartService tbCartService;

    @GetMapping(value = "/cart/getAll")
    @ApiOperation(value = "分页获取购物车信息")
    public ResponseEntity<ViewListResult> getAllCart(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ViewListResult all = this.tbCartService.getAll(pageNum, pageSize);
        return ResponseEntity.ok(all);
    }

    @GetMapping(value = "/cart/get/{cartId}")
    @ApiOperation(value = "根据Id查询")
    public TbCart getTbCartById(@PathVariable("cartId") Long cartId) {
        if(StringUtils.isEmpty(cartId)){
            return new TbCart();
        }
        TbCart tbCart = this.tbCartService.getTbCartById(cartId);
        return tbCart;
    }

    @PostMapping(value = "/cart/save")
    @ApiOperation(value = "form表单的形式保存购物车数据")
    public ResponseEntity<String> saveTbCart(TbCart cart) {
        if(StringUtils.isEmpty(cart.getUserId())||StringUtils.isEmpty(cart.getItemId())){
            return ResponseEntity.ok("请填写必要的信息");
        }
        Integer integer = this.tbCartService.saveTbCart(cart);
        if (integer == 1)
            return ResponseEntity.ok("ok");
        else
            return ResponseEntity.ok("fault");
    }

    /**
     * 保存一条商品信息，传入的数据是json
     * @param cartJson
     * @return
     */
    @PostMapping(value = "/cart/save/json")
    @ApiOperation(value = "json格式保存购物车数据")
    public ResponseEntity<String> saveTbCart(@ApiParam(required = true,value = "样例：{\"userId\":10,\n\"itemId\":4734102,\n\"itemTitle\":\"xx\",\n\"itemImage\":\"xx\",\n\"itemPrice\":3299,\n \"num\":1}")
                                                 @RequestBody
                                                         String cartJson) {
        if(StringUtils.isEmpty(cartJson)){
            return ResponseEntity.ok("json数据不能为空！");
        }
        TbCart tbCart = JsonUtils.jsonToPojo(cartJson, TbCart.class);
        Integer integer = this.tbCartService.saveTbCart(tbCart);
        if (integer == 1)
            return ResponseEntity.ok("ok");
        else
            return ResponseEntity.ok("fault");
    }

    /**
     * 根据Id删除购物车商品
     * @param id
     * @return
     */
    @DeleteMapping(value = "/cart/delete/{id}")
    @ApiOperation(value = "根据Id删除")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id){
        if(StringUtils.isEmpty(id)){
            return ResponseEntity.ok("id不能为空！");
        }
        this.tbCartService.deleteTbCartById(id);
        return ResponseEntity.ok("ok");
    }

    /**
     * 根据Id更新购物车数据
     * @param cart
     * @return
     */
    @PutMapping(value = "/cart/update")
    @ApiOperation(value = "根据Id更新购物车数据")
    public ResponseEntity<String> updateTbCart(TbCart cart) {
        if(StringUtils.isEmpty(cart.getId())||StringUtils.isEmpty(cart.getUserId())||StringUtils.isEmpty(cart.getItemId())){
            return ResponseEntity.ok("请填写必要的信息");
        }
        Integer integer = this.tbCartService.updateTbCartById(cart);
        if (integer == 1)
            return ResponseEntity.ok("ok");
        else
            return ResponseEntity.ok("fault");
    }

}
