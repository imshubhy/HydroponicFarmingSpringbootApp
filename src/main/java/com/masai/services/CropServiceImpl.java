package com.masai.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.NotFoundException;
import com.masai.model.Crop;
import com.masai.model.User;
import com.masai.repository.CropDao;
import com.masai.util.GetCurrentLoginUserSessionDetailsIntr;


@Service
public class CropServiceImpl implements CropServiceIntr {
  
	@Autowired
	private CropDao crop_dao;
	
	@Autowired
    private GetCurrentLoginUserSessionDetailsIntr getCurrentLoginUser;
	
	@Override
	public Crop addCrop(Crop crop,String key) throws NotFoundException {
		// TODO Auto-generated method stub
	 User current_user =   getCurrentLoginUser.getCurrentUser(key);
	 current_user.getCrop().add(crop);
	 crop.setUser(current_user);
	 
		return crop_dao.save(crop);
	}

	@Override
	public List<Crop> showListOfCrops(String key) {
		 User current_user =   getCurrentLoginUser.getCurrentUser(key);
	  List<Crop> list =	 current_user.getCrop();
	  if(list.size() == 0)
		  throw new NotFoundException("Crop not found..");
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public Crop updateCropDetails(Crop crop, Integer id, String key) throws NotFoundException {
		// TODO Auto-generated method stub
		 User current_user =   getCurrentLoginUser.getCurrentUser(key);
		 List<Crop> cropList =	 current_user.getCrop();
		 if(cropList.size() == 0)
			  throw new NotFoundException("Crop not found..");
		  Optional<Crop> optCrop = crop_dao.findById(id);
		  if(!optCrop.isPresent())
			  throw new NotFoundException("Crop not found with id : " + id);
		  
		  Crop foundCrop = optCrop.get();
		  
		  foundCrop.setCrop_name(crop.getCrop_name());
		  foundCrop.setCrop_type(crop.getCrop_type());
		  
		return crop_dao.save(foundCrop);
	}

	

}
