package jp.tst.audittool.apiresource.repositoryimpl;


import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jp.tst.audittool.apiresource.entity.EquipmentInfo;
import jp.tst.audittool.apiresource.repository.AbstractRepository;
import jp.tst.audittool.apiresource.repository.EquipmentInfoRepository;

@Repository("EquipmentInfoRepository")
@Transactional
public class EquipmentInfoRepositoryImp extends AbstractRepository<String, EquipmentInfo> implements EquipmentInfoRepository {

  @Override
  @SuppressWarnings("unchecked")
  public List<EquipmentInfo> findAllEquipmentInfo() {
    
    Criteria criteria = createEntityCriteria().addOrder(Order.asc("updatedAt"));
    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    criteria.add(Restrictions.eq("isDeleted", false));
    List<EquipmentInfo> equipmentInfoList = (List<EquipmentInfo>) criteria.list();
    
    return equipmentInfoList;
  }
}
