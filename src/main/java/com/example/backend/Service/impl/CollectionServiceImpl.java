package com.example.backend.Service.impl;

import com.example.backend.Dao.CarDao;
import com.example.backend.Dao.CollectionDao;
import com.example.backend.Service.CollectionService;
import com.example.backend.pojo.Entity.CarEntity;
import com.example.backend.pojo.Entity.CollectionEntity;
import com.example.backend.pojo.Vo.car.CarCollectionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private CarDao carDao;

    @Override
    public boolean isCollection(Long userId, Long carId) {
        CollectionEntity collectionEntity = collectionDao.findByUserIdAndCarId(userId, carId);
        return collectionEntity != null;
    }

    @Override
    public void addCollection(Long userId, Long carId) {
        CollectionEntity collectionEntity = CollectionEntity.builder().carId(carId).userId(userId).build();
        collectionDao.save(collectionEntity);
    }

    @Override
    public void deleteCollection(Long userId, Long carId) {
        CollectionEntity collectionEntity = collectionDao.findByUserIdAndCarId(userId, carId);
        collectionDao.delete(collectionEntity);
    }

    @Override
    public List<CarCollectionVo> getCollections(Long userId) {
        List<CollectionEntity> collectionEntities = collectionDao.findByUserId(userId);
        List<CarCollectionVo> carCollectionVos = new ArrayList<>();
        for (CollectionEntity collectionEntity: collectionEntities){
            Long carId = collectionEntity.getCarId();
            CarEntity carEntity = carDao.findById(carId).get();
            CarCollectionVo carCollectionVo = CarCollectionVo.builder().id(carEntity.getId())
                    .name(carEntity.getBrand() + ' ' + carEntity.getCarType())
                    .price(carEntity.getPrice()).time(carEntity.getCarAge())
                    .mileage(carEntity.getCarDistance()).source(carEntity.getPosition())
                    .picturePath(carEntity.getPicture()).build();
            carCollectionVos.add(carCollectionVo);
        }
        return carCollectionVos;
    }
}
