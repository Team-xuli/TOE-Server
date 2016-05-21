package team.xuli.toe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import team.xuli.toe.dao.IItemTypeDao;
import team.xuli.toe.domain.ItemType;

import java.util.List;

/**
 * @author: 徐清锋
 * 创建时间：2016/5/20
 * 创建原因：
 */
@RestController
public class ItemTypeController {
    @Autowired
    IItemTypeDao itemTypeDao;

    @RequestMapping(value = "/itemTypes", method = RequestMethod.GET)
    List<ItemType> getItemTypes(){
        return itemTypeDao.getItemTypes();
    }
}
