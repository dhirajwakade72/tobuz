import { Component, ViewChild } from '@angular/core';

import { FormBuilder, FormGroup } from '@angular/forms';
import { CommonService } from 'src/app/services/common.service';

import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-filters',
  templateUrl: './filters.component.html',
  styleUrls: ['./filters.component.css']
})
export class FiltersComponent {
  
allCategories: any;
allBSType: any;
allCountries: any;

isInvestmentFilterVisible:any=true;
categoriesFilterVisible:any=true;
isBSTFilterVisible:any=true;

categoriesIds: number[] = [];
countryIds: number[] = [];
investmentFilter: string[] = [];
bSTValueId: number[] = [];

constructor(private commonService:CommonService,private dataService:DataService,private fb: FormBuilder){}

ngOnInit() {
  this.subscribeData();
  this.getCategories();
  this.getCountries();
  this.getBusinessServiceType();
   
}


  subscribeData(){
    this.dataService.investmentFilterVisible$.subscribe(
      data => {
        this.isInvestmentFilterVisible=data;
      }
    );

    this.dataService.bstFilterVisible$.subscribe(
      data => {
        this.isBSTFilterVisible=data;
      }
    );

    this.dataService.categoriesFilterVisible$.subscribe(
      data => {
        this.categoriesFilterVisible=data;        
      }
    );

  }
  public getCategories()
  {
    
    this.commonService.getCategories().subscribe(
      (response) => {        
        this.allCategories=response.result;      
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  public getBusinessServiceType()
  {
    
    this.commonService.getBusinessServiceType().subscribe(
      (response) => {        
        this.allBSType=response.result;      
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

onCategoriesCheckboxChange(e:any) 
{ 
  if (e.target.checked && !this.categoriesIds.includes(e.target.value)) 
  {     
   this.categoriesIds.push(e.target.value);
  } 
  else
  { 
    this.categoriesIds = this.categoriesIds.filter(item => item !== e.target.value);
  }
  
  this.dataService.updateCategories(this.categoriesIds);
}

onCountryCheckboxChange(e:any) 
{ 
 
  if (e.target.checked && !this.countryIds.includes(e.target.value)) 
  {     
   this.countryIds.push(e.target.value);
  } 
  else
  { 
    this.countryIds = this.countryIds.filter(item => item !== e.target.value);
  } 
  
  this.dataService.updateCountry(this.countryIds); 
}

onInvestmentFilterCheckboxChange(e:any) 
{ 
  if (e.target.checked && !this.investmentFilter.includes(e.target.value)) 
  {     
   this.investmentFilter.push(e.target.value);
  } 
  else
  { 
    this.investmentFilter = this.investmentFilter.filter(item => item !== e.target.value);
  }
  this.dataService.updateInvestmentFilterValue(this.investmentFilter);
}

onBSTCheckboxChange(e:any) 
{ 
  if (e.target.checked && !this.bSTValueId.includes(e.target.value)) 
  {     
   this.bSTValueId.push(e.target.value);
  } 
  else
  { 
    this.bSTValueId = this.bSTValueId.filter(item => item !== e.target.value);
  }  
  this.dataService.updateBSTFilterValue(this.bSTValueId);
}

  clearAllFilter()
  {
    this.dataService.updateSearchFunctionEnable(false); 
    this.countryIds.map(item => {
      this.clearCheckBox('country_' + item)
    });
    this.countryIds=[];
    this.dataService.updateCountry(this.countryIds);

    if(this.categoriesFilterVisible)
    {
      this.categoriesIds.map(item => {
        this.clearCheckBox('catgories_' + item)
      });
      this.categoriesIds=[];
      this.dataService.updateCategories(this.categoriesIds);
    }
    
    if(this.isBSTFilterVisible)
    this.dataService.updateBSTFilterValue(this.bSTValueId);
    if(this.isInvestmentFilterVisible)
    this.dataService.updateInvestmentFilterValue(this.investmentFilter);
    
    this.dataService.updateSearchFunctionEnable(true);


  }

  public clearCheckBox(id:string)
  {
    const countryCheckBox = document.getElementById(id) as HTMLInputElement;
    if(countryCheckBox) {
      countryCheckBox.checked = false;
    }
  }

}


