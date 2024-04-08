package com.tobuz.dto;

import lombok.Data;

@Data
public class LikeDislikeDTO 
{
	private String listingType;
	private Long listingId;
	private String likeOrDisLike;

}
