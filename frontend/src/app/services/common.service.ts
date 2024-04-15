import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Defination } from '../definition/defination';
import { Meta, Title } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor(private httpClient: HttpClient) { }

  public getCountries():Observable<any>
  {
      return this.httpClient.get(Defination.HOST+Defination.GET_COUNTRY_LIST);
  }
  public getStateByCountryId(couuntryId:number):Observable<any>
  {
      return this.httpClient.get(Defination.HOST+Defination.GET_STATE_BY_COUNTRY_ID+"/"+couuntryId);
  }
  public getCategories():Observable<any>
  {
    return this.httpClient.get(Defination.HOST+Defination.GET_CATEGORIES);
  }

  public getSubCategories():Observable<any>
  {
    return this.httpClient.get(Defination.HOST+Defination.GET_CATEGORIES);
  }

  public getBusinessServiceType():Observable<any>
  {
    return this.httpClient.get(Defination.HOST+Defination.GET_BUSINESS_SERVICE_TYPE);
  }

  public getUserMenu(userrole:string):Observable<any>
  {
    return this.httpClient.get(Defination.HOST+Defination.GET_USER_MENU+"/"+userrole);
  }

  public uploadFile(formData: FormData): Observable<any> {
    return this.httpClient.post(Defination.HOST + Defination.POST_FILE_UPLOAD, formData);
  }


}
