package jp.tst.audittool.apiresource.repository;

import java.util.List;
import jp.tst.audittool.apiresource.entity.EquipmentInfo;

public interface EquipmentInfoRepository {
  List<EquipmentInfo> findAllEquipmentInfo();
}
