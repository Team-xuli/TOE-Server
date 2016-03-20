package team.xuli.toe.dao;

import org.apache.ibatis.annotations.Param;
import team.xuli.toe.domain.Order;

import java.util.List;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/19
 * 创建原因：
 */
public interface IOrderDao {
    boolean insert(Order order);
    boolean update(Order order);
    Order get(int orderId);

    List<Order> getQualifiedOrders(@Param("startPos") int startPos,
                          @Param("count") int count,
                          @Param("ownerId")int ownerId,
                          @Param("carrierId")int carrierId,
                          @Param("status")int status);

    int getQualifiedOrdersCount(@Param("ownerId")int ownerId,
                          @Param("carrierId")int carrierId,
                          @Param("status")int status);
}
