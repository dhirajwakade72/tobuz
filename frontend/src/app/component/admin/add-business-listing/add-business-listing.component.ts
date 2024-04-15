import { Component, ElementRef, Renderer2, ViewChild } from '@angular/core';
import { AddListingDTO } from 'src/app/dto/add-listing-dto';
import { CommonService } from 'src/app/services/common.service';
import {NgForm } from '@angular/forms';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { Router } from '@angular/router';
import { DataService } from 'src/app/services/data.service';
import { ActiveUserDto } from 'src/app/dto/active-user-dto';
import { Defination } from 'src/app/definition/defination';
import { Meta, Title } from '@angular/platform-browser';
@Component({
  selector: 'app-add-business-listing',
  templateUrl: './add-business-listing.component.html',
  styleUrls: ['./add-business-listing.component.css']
})
export class AddBusinessListingComponent {

  @ViewChild('businessType') businessType!: ElementRef;
  @ViewChild('category') category!: ElementRef;
  @ViewChild('subcategory') subcategory!: ElementRef;
  @ViewChild('desc') desc!: ElementRef;
  @ViewChild('contactNumber') contactNumber!: ElementRef;

  allCategories: any;
  allSubCategories: any;
  allCountries:any;
  selectedStates:any;
  selectedCurrencyCode:string='';
  selectedLisistingFiles: File[] = [];
  selectedLisistingFilesName: String[] = [];
  selectLogoFile: File[] = [];
  selectLogoFileName: String[] = [];
  documentFilesList:File[] = [];
  documentFilesListName:String[] = [];
  isUSerLoggedIn:boolean=false;
  activeUser:ActiveUserDto;
  isContactUser:string='Y';
  listingStatus:string='DRAFTED';
  formData: AddListingDTO = new AddListingDTO();
  isfileLogUploadError:boolean=false;
  fileLogUploadError:string='';

  isfileListingUploadError:boolean=false;
  fileListingUploadError:string='';

  isfileDocUploadError:boolean=false;
  fileDocUploadError:string='';
  constructor(private title:Title,private meta:Meta, private commonService:CommonService,private renderer: Renderer2,private dataService:DataService,private router: Router,private businessService:BusinessListingService){ 
    this.activeUser=new ActiveUserDto();
  }
  ngOnInit() {
   this.dataService.checkUserLoginData();
   this.activeUser=this.dataService.getActiveUserDetails();
   this.updateMeta();
    this.getCategories();  
    this.getCountries();   
  }

  updateMeta()
  {
    this.title.setTitle('Tobuz.com | Add Listing');
    this.meta.updateTag({name: 'description', content: 'tobuz.com : Add Listing page'});
    this.meta.updateTag({ name: 'title', content: this.title.getTitle() });
    this.dataService.addCommanMeta(this.title,this.meta);
  }

  public uploadListingFile(selectedListingFiles: File[],type:string): void {
    const formData = new FormData();
    selectedListingFiles.forEach(file => {
      formData.append('files', file);
    });

    this.commonService.uploadFile(formData).subscribe(
      (response: any) => {              
        
        if(response.status="SUCCESS")
        {
          this.isfileDocUploadError=false;
          this.isfileLogUploadError=false;
          this.isfileListingUploadError=false;
          if(type==='LISTING')
          {
            this.selectedLisistingFilesName=response.result;
            
          }
          else if(type==='LOGO')
          {      
            this.selectLogoFileName=response.result;
          }
          else if(type==='DOCUMENT')
          { 
            this.documentFilesListName=response.result;
          }          
        }
        else
        {
          alert("Failed to updoad files");
        }
        
      },
      (error) => {

        if(type==='LISTING')
        {        
        
          this.isfileListingUploadError=true;
          this.fileListingUploadError="File size exceeds limit of 10 MB";
        }
        else if(type==='LOGO')
        {      
          this.fileLogUploadError="File size exceeds limit of 10 MB";
          this.isfileLogUploadError=true;
          
        }
        else if(type==='DOCUMENT')
        { 
          this.fileDocUploadError="File size exceeds limit of 10 MB"
          this.isfileDocUploadError=true;
          
        }  
      }
    );
  }

  public uploadFile(type:string)
  {
    if(type==='LISTING')
    {
     
      this.uploadListingFile(this.selectedLisistingFiles,type);           
    }
    else if(type==='LOGO')
    { 
      
      this.uploadListingFile(this.selectLogoFile,type);
    }
    else if(type==='DOCUMENT')
    { 
      
      this.uploadListingFile(this.documentFilesList,type);
    }
    
  }


  removeFile(index: number,type:string) {
    
    if(type==='LISTING')
    {
      this.selectedLisistingFiles.splice(index, 1);      
    }
    else if(type==='LOGO')
    {      
      this.selectLogoFile.splice(index, 1);
    }
    else if(type==='DOCUMENT')
    { 
      this.documentFilesList.splice(index, 1);
    }
  }

  handleFileChange(event: any,type:string) {    
    if(type==='LISTING')
    {
      this.isfileListingUploadError=false;
      const files: FileList = event.target.files;    
      for (let i = 0; i < files.length; i++) {
        this.selectedLisistingFiles.push(files[i]);
      }
    }
    else if(type==='LOGO')
    {
      this.isfileLogUploadError=false;     
      const files: FileList = event.target.files;    
      //for (let i = 0; i < files.length; i++) {
        this.removeFile(0,'LOGO');
        this.selectLogoFile.push(files[0]);
        
      //}
    }
    else if(type==='DOCUMENT')
    {      
      this.isfileDocUploadError=false;
      const files: FileList = event.target.files;    
      for (let i = 0; i < files.length; i++) {
        this.documentFilesList.push(files[i]);
      }
    }
  }

  onDraft(form:NgForm) {   
    if (form.valid) { 
    form.value.status=Defination.DRAFT_LISTING_STATUS;
    this.submitForm(form,"Successfully Drafted");
    }
    else {
      alert("Please fill few details");
    }
  }

  onSubmit(form:NgForm) {    
    if (form.valid) {
      form.value.status=Defination.UNDERVIEW_LISTING_STATUS;
      this.submitForm(form,"Succefully Published");
    } else {
      alert("Please fill required data");
    }
  }

  submitForm(form:NgForm,message:string)
  {   
      form.value.appUserId = this.activeUser.userId;
      form.value.listingGallary=this.selectedLisistingFilesName;
      form.value.logoFile=this.selectLogoFileName[0];
      

      this.businessService.addListing(form.value).subscribe(
        (response:any) => {              
                    
          if(response.status=='SUCCESS')
          {
            alert(message);
            form.resetForm();
          }
          else
          {
            alert("Failed..Try Again");
          }
        },
        (error) => {
          console.error('Error:', error);
        }
      );
  }

  public getCategories()
  {    
    this.commonService.getSubCategories().subscribe(
      (response) => {                
        this.allCategories=response.result;  
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  public getStateByCountryId(countryId:number)
  {    
    this.commonService.getStateByCountryId(countryId).subscribe(
      (response) => {                
        this.selectedStates=response.result;
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  public getCountries()
  {    
    this.commonService.getCountries().subscribe(
      (response) => {                
        this.allCountries=response.result;
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }


  onSelect(event: Event) {
    const categoryId = (event.target as HTMLSelectElement).value;
    
    const selectedCategoryId = parseInt(categoryId, 10);
    if (!isNaN(selectedCategoryId)) {     
      const selcategory = this.allCategories.find((cat: { id: number }) => cat.id === selectedCategoryId);      
      this.allSubCategories=selcategory.subCategoryList;
    }
  }

  onCountrySelect(event: Event) {
    const countryId = (event.target as HTMLSelectElement).value;
    const selectedCountryId = parseInt(countryId, 10);
    if (!isNaN(selectedCountryId)) {
      const selectedCountry=this.allCountries.find((c: { id: number }) => c.id === selectedCountryId);      
      const currencyCode=selectedCountry.currencyCode;
      this.selectedCurrencyCode=currencyCode;
      this.getStateByCountryId(selectedCountryId);
    }
  }


handleClick(value: string) 
{ 
    const y_boi = document.getElementById('Y_BOI');
    const n_boi = document.getElementById('N_BOI');
    if('Y'===value)
    {
      this.renderer.addClass(y_boi, 'active');
      this.renderer.addClass(n_boi, 'notActive');
      this.renderer.removeClass(y_boi, 'notActive');
      this.renderer.removeClass(n_boi, 'active');

    }
    else
    { 
      this.renderer.addClass(n_boi, 'active');
      this.renderer.addClass(y_boi, 'notActive');
      this.renderer.removeClass(n_boi, 'notActive');
      this.renderer.removeClass(y_boi, 'active');
    }
    this.isContactUser=value; 
}  

public validation()
  {    

    
    if(this.formData.contactNumber==='' )
    {
      this.contactNumber.nativeElement.focus();
    }else if(this.formData.contactNumber.length < Defination.MOBILE_MIN_LENGTH || this.formData.contactNumber.length > Defination.MOBILE_MAX_LENGTH)
    { 
      const contact = document.getElementById('contactNumber_msg');
      if (contact) {
        contact.textContent = `Contact number should be between ${Defination.MOBILE_MIN_LENGTH} to ${Defination.MOBILE_MAX_LENGTH} characters.`;
      }
        this.contactNumber.nativeElement.focus();
    }    

  }


  onPressMobile(event: any) {
    const inputValue = event.target.value;
    if (inputValue.length >= 14) {      
      event.preventDefault(); 
    }
  }

}
