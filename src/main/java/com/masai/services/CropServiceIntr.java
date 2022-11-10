package com.masai.services;

import java.util.List;

import com.masai.exception.NotFoundException;
import com.masai.model.Crop;

public interface CropServiceIntr {
	
	public Crop addCrop(Crop crop, String key) throws NotFoundException;
	
	public List<Crop> showListOfCrops(String key);
	
	public Crop updateCropDetails(Crop crop ,Integer id, String key) throws NotFoundException;

}
