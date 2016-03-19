package team.xuli.toe.dao;

import org.apache.ibatis.annotations.Param;
import team.xuli.toe.domain.Address;

import java.util.List;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/19
 * 创建原因：
 */
public interface IAddressDao {
    boolean insert(Address address);

    Address get(int addressId);

    List<Address> getAddresses(@Param("userId")int userId, @Param("type")int type);

    boolean update(Address address);


}
