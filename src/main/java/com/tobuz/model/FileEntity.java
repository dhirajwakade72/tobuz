package com.tobuz.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class FileEntity extends BaseEntity {

	public BusinessListing getBusinessListing() {
		return businessListing;
	}

	public void setBusinessListing(BusinessListing businessListing) {
		this.businessListing = businessListing;
	}

	private String fileName;

	private String mimeType;

	private String filePath;

	private String thumbnailPath;

	private String thumbnailKey;

	public String getThumbnailKey() {
		return thumbnailKey;
	}

	public void setThumbnailKey(String thumbnailKey) {
		this.thumbnailKey = thumbnailKey;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	@ManyToOne
	public AppUser uploadedBy;

	@Lob
	private byte[] byteContent;

	private Date uploadedOn;

	private String fileKey;

	@Column(columnDefinition = "TEXT")
	private String comment;

	@ManyToOne
	private BusinessListing businessListing;

	private String fileType = "IMAGE";

	
//	 public static com.avaje.ebean.Model.Finder<Long, FileEntity> find = new
//	  com.avaje.ebean.Model.Finder<Long, FileEntity>(FileEntity.class);
//	
	public String getFileName() {
		return fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public byte[] getByteContent() {
		return byteContent;
	}

	public void setByteContent(byte[] byteContent) {
		this.byteContent = byteContent;
	}

	public Date getUploadedOn() {
		return uploadedOn;
	}

	public void setUploadedOn(Date uploadedOn) {
		this.uploadedOn = uploadedOn;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public AppUser getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(AppUser uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	/*
	 * public static FileEntity toFileEntity(FileEntity fileEntity,FilePart
	 * filePart,String cloudURL,String fileKey) { if(fileEntity == null) {
	 * fileEntity = new FileEntity(); fileEntity.fileName = filePart.getFilename();
	 * fileEntity.mimeType = filePart.getContentType();
	 * fileEntity.setUploadedBy(LoginController.getLoggedInUser());
	 * fileEntity.setUploadedOn(new Date()); fileEntity.setFilePath(cloudURL);
	 * fileEntity.setFileKey(fileKey); fileEntity.save(); }else {
	 * fileEntity.fileName = filePart.getFilename(); fileEntity.mimeType =
	 * filePart.getContentType();
	 * fileEntity.setUploadedBy(LoginController.getLoggedInUser());
	 * fileEntity.setUploadedOn(new Date()); fileEntity.setFilePath(cloudURL);
	 * fileEntity.setFileKey(fileKey); fileEntity.update(); } return fileEntity; }
	 * 
	 * public static List<FileEntity> getBusinessListingFileEntities(BusinessListing
	 * businessListing,Integer pageSize,Integer pageNum){ return
	 * FileEntity.find.where().eq("businessListing", businessListing).eq("fileType",
	 * "IMAGE").findPagedList(pageNum, pageSize).getList(); }
	 * 
	 * public static List<FileEntity> getBusinessListingFileEntities(BusinessListing
	 * businessListing){ return FileEntity.find.where().eq("businessListing",
	 * businessListing).eq("fileType",
	 * "IMAGE").orderBy().asc("createdOn").findList(); }
	 * 
	 * 
	 * public static List<FileEntity> getBusinessListingDocuments(BusinessListing
	 * businessListing){ return FileEntity.find.where().eq("businessListing",
	 * businessListing).eq("fileType", "DOCUMENT").findList(); } public static
	 * Function<FileEntity, String> getfilePath = fileEntity -> { if (fileEntity !=
	 * null && fileEntity.getFilePath() != null) { //return
	 * Constants.AMAZON_S3_THUMBNAILS_BUCKET_NAME + fileEntity.getFileKey();
	 * //System.out.println("fileEntity.getThumbnailPath()=====>"+fileEntity.
	 * getThumbnailPath()); return fileEntity.getFilePath(); } return null; };
	 *//**
		 * Action : For geeting all tobuz images for SEO
		 * 
		 * @return
		 *//*
			 * public static List<FileEntityBean> getSEOImages() { List<FileEntityBean>
			 * beanList = new ArrayList<>(); String rawSql = "";
			 * 
			 * 
			 * SELECT fe.file_path, bl.title, bl.business_address_country_id ,
			 * bl.suggested_title, bl.latitude,bl.longitude from file_entity as fe inner
			 * join business_listing as bl on fe.business_listing_id = bl.id and
			 * fe.is_active='true' order by fe.created_on desc FETCH FIRST 1000 ROW ONLY;
			 * 
			 * 
			 * rawSql =
			 * "SELECT fe.file_path, bl.title, bl.business_address_country_id , bl.suggested_title, bl.latitude,bl.longitude "
			 * + "FROM file_entity  as fe" +
			 * " INNER JOIN  business_listing as bl ON fe.business_listing_id = bl.id and fe.is_active='true'"
			 * + " order by fe.created_on desc FETCH FIRST 1000 ROW ONLY";
			 * 
			 * SqlQuery sqlQuery = Ebean.createSqlQuery(rawSql); List<SqlRow> rows=
			 * sqlQuery.findList(); for (SqlRow sqlRow : rows) { FileEntityBean bean = new
			 * FileEntityBean(); bean.setImageURL(sqlRow.getString("file_path"));
			 * bean.setTitleOfImage(sqlRow.getString("title"));
			 * bean.setCaptionOfImage(sqlRow.getString("suggested_title"));
			 * if(sqlRow.getLong("business_address_country_id")!=null){ Country country =
			 * Country.find.byId(sqlRow.getLong("business_address_country_id"));
			 * bean.setLocationOfImage(country.getName()); }
			 * bean.setLatitude(sqlRow.getDouble("latitude"));
			 * bean.setLongitude(sqlRow.getDouble("longitude"));
			 * bean.setUrlToLicenseImage("file_path"); beanList.add(bean); } return
			 * beanList; }
			 */
}
