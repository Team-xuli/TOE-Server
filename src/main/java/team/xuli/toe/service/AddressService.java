package team.xuli.toe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.xuli.toe.dao.IAddressDao;
import team.xuli.toe.domain.Address;
import team.xuli.toe.domain.User;
import team.xuli.toe.util.AppConst;

import java.util.List;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/19
 * 创建原因：
 */
@Service
public class AddressService implements IAddressService {
    @Autowired
    private IAddressDao addressDao;

    public boolean addOrgAddress(Address address) {
        address.setType(AppConst.ADDRESS_TYPE_ORG);
        return addressDao.insert(address);
    }

    public List<Address> getOrgAddresses(int userId) {
        return addressDao.getAddresses(userId, AppConst.ADDRESS_TYPE_ORG);
    }

    public boolean updateOrgAddress(Address address) {
        return addressDao.update(address);
    }

    public boolean validateAddressModifier(User user, Address address) {
        Address oldAddress = addressDao.get(address.getAddressId());
        return oldAddress.getUserId() != user.getUserId() &&
                oldAddress.getType() == AppConst.ADDRESS_TYPE_ORG;
    }
}
