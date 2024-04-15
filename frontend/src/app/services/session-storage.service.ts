import { Injectable } from '@angular/core';
import { DataService } from './data.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {

    
  constructor(private router: Router) 
  { 

  }

  addItem(key:string,value:any)
  {
    localStorage.setItem(key,value);
  }
  getItem(key:string):any
  {
    return localStorage.getItem(key);    
  }
  removeItem(key:string)
  {
    localStorage.removeItem(key);
  }   

  clear()
  {
    localStorage.clear();
  }   


addUserSession(data:any)
{ 
  if (data) {
    localStorage.setItem("USER_MAIL", data.email);
    localStorage.setItem("USER_ID", data.id);
    localStorage.setItem("USER_NAME", data.name);
    localStorage.setItem("USER_ROLE", data.userDefaultRole);
  }  
}


}
