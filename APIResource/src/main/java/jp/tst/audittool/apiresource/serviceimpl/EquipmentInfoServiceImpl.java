package jp.tst.audittool.apiresource.serviceimpl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jp.tst.audittool.apiresource.entity.EquipmentInfo;
import jp.tst.audittool.apiresource.repository.EquipmentInfoRepository;
import jp.tst.audittool.apiresource.service.EquipmentInfoService;

/**
 * The Class EquipmentInfoServiceImpl.
 */
@Service
public class EquipmentInfoServiceImpl implements EquipmentInfoService {

  /** The EquipmentInfoServiceImpl logger. */
  private static final Logger logger = LoggerFactory.getLogger(EquipmentInfoServiceImpl.class);

  @Autowired
  private EquipmentInfoRepository equipmentInfoRepository;

  @Override
  public ResponseEntity<?> getAllEquipmentInfo(HttpServletRequest req) {
    logger.info("IN - getAllEquipmentInfo()");
    List<EquipmentInfo> equipmentInfoList = equipmentInfoRepository.findAllEquipmentInfo();

    try {
      if (equipmentInfoList.size() > 0) {
        logger.info("OUT - getAllEquipmentInfo()");
        return new ResponseEntity<>(equipmentInfoList, HttpStatus.OK);
      }
      logger.info("OUT - getAllEquipmentInfo()");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      logger.error("Exception: {}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
