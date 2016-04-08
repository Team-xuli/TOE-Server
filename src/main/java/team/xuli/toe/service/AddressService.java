package team.xuli.toe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.xuli.toe.dao.IAddressDao;
import team.xuli.toe.domain.Address;
import team.xuli.toe.domain.User;
import team.xuli.toe.util.AppConst;
import team.xuli.toe.util.Messages;

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
    @Autowired
    private ISecurityService securityService;

    public boolean addOrgAddress(Address address) {
        User currentUser = securityService.currentUser();
        this.validateNewAddress(address);
        address.setUserId(currentUser.getUserId());
        address.setType(AppConst.ADDRESS_TYPE_ORG);
        return addressDao.insert(address);
    }
    public boolean addDestAddress(Address address){
        User currentUser = securityService.currentUser();
        address.setUserId(currentUser.getUserId());
        address.setType(AppConst.ADDRESS_TYPE_DEST);
        return addressDao.insert(address);
    }

    public List<Address> getOrgAddresses() {
        User currentUser = securityService.currentUser();
        return addressDao.getAddresses(currentUser.getUserId(), AppConst.ADDRESS_TYPE_ORG);
    }

    public boolean updateOrgAddress(Address address) {
        User currentUser = securityService.currentUser();
        this.validateAddressModifier(currentUser, address);
        return addressDao.update(address);
    }

    public boolean validateAddressModifier(User user, Address address) {
        Address oldAddress = addressDao.get(address.getAddressId());
        if(oldAddress.getUserId() == user.getUserId() &&
                oldAddress.getType() == AppConst.ADDRESS_TYPE_ORG){
            return true;
        } else {
            throw new RuntimeException(Messages.DANGEROUS_REQUEST);
        }
    }
    public boolean validateNewAddress(Address address){
        if(address.getCalledName() != null && address.getCalledName() != "" &&
            address.getPhoneNo() != null && address.getPhoneNo() != "" &&
            address.getAddressDesc() != null && address.getAddressDesc() != ""){
            return true;
        } else {
            throw new RuntimeException(Messages.INFO_REQUIRED);
        }

    }
}
