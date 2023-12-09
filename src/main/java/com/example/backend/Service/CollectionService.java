package com.example.backend.Service;

import com.example.backend.pojo.Vo.car.CarCollectionVo;

import java.util.List;

public interface CollectionService {

    public boolean isCollection(Long userId, Long carId);

    public void addCollection(Long userId, Long carId);

    public void deleteCollection(Long userId, Long carId);

    public List<CarCollectionVo> getCollections(Long userId);
}
