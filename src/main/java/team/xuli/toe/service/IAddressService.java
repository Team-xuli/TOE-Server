package team.xuli.toe.service;

import team.xuli.toe.domain.Address;
import team.xuli.toe.domain.User;

import java.util.List;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/19
 * 创建原因：
 */
public interface IAddressService {
    boolean addOrgAddress(Address address);
    boolean updateOrgAddress(Address address);
    List<Address> getOrgAddresses(int userId);
    boolean validateAddressModifier(User user, Address address);
}
