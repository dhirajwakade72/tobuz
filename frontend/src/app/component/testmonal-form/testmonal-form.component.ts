import { Component, ElementRef, Renderer2, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Meta, Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { BusinessListingService } from 'src/app/services/business-listing.service';
import { CommonService } from 'src/app/services/common.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-testmonal-form',
  templateUrl: './testmonal-form.component.html',
  styleUrls: ['./testmonal-form.component.css']
})
export class TestmonalFormComponent {
  registerForm: FormGroup;
  @ViewChild('name') name!: ElementRef;
  @ViewChild('aboutUser') aboutUser!: ElementRef;
  @ViewChild('email') email!: ElementRef;
  @ViewChild('message') message!: ElementRef;
  selectedLisistingFiles: File[] = [];
  selectedLisistingFilesName: string[] = [];
  showMessage: string = '';
  constructor(private meta: Meta, private title: Title,
    private formBuilder: FormBuilder,
    private router: Router,
    private el: ElementRef,
    private renderer: Renderer2,
    private businessListingService: BusinessListingService,
    private commonService: CommonService,
    private dataService:DataService) {

    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      aboutUser: ['', Validators.required],
      email: ['', Validators.required],
      message: ['', Validators.required]
    });
  }
  ngOnInit() {

    this.updateMeta();
  }


  updateMeta() {
    this.title.setTitle("Tobuz.com | TestMonal form");
    this.meta.updateTag({ name: 'description', content: "Tobuz is a Global Marketplace for Buying, Franchising, Selling, Funding, or Transforming your business. Search the extensive database of 36940 Businesses, Consultants and Advisors." });
    this.dataService.addCommanMeta(this.title,this.meta);
  }

  handleFileChange(event: any) {
    const files: FileList = event.target.files;
    this.removeFile(0);
    // for (let i = 0; i < files.length; i++) {
    this.selectedLisistingFiles.push(files[0]);

    //   }

  }

  public uploadListingFile(selectedListingFiles: File[]): void {
    const formData = new FormData();
    selectedListingFiles.forEach(file => {
      formData.append('files', file);
    });

    this.commonService.uploadFile(formData).subscribe(
      (response: any) => {

        if (response.status = "SUCCESS") {
          this.selectedLisistingFilesName = response.result;

          //alert(response.message);
        }
        else {
          //alert("Failed to updoad files");
        }

      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  removeFile(index: number) {
    this.selectedLisistingFiles.splice(index, 1);

  }

  onSubmit() {
    if (this.validation()) {
      return;
    }
    else {

      this.uploadListingFile(this.selectedLisistingFiles);

      this.businessListingService.addTestimonial(this.registerForm.value.name, this.registerForm.value.email, this.registerForm.value.message, this.registerForm.value.aboutUser, this.selectedLisistingFilesName[0]).subscribe(
        (data: any) => {
          if (data.status == "FAILED") {
            alert(data.message);
          }
          else {
            alert("Successfully sumbitted");
            this.router.navigate(["/testmonal"]);

          }

        },
        error => {
          alert("FAIELD");
        });
    }
  }
  validation(): boolean {

    if (this.registerForm.value.name === '') {
      this.showMessage = "Please enter name ";
      this.name.nativeElement.focus();
      return true;
    }
    else if (this.registerForm.value.email === '') {
      this.showMessage = "Please enter email ";
      this.email.nativeElement.focus();
      return true;
    }
    else if (!/\S+@\S+\.\S+/.test(this.registerForm.value.email)) {
      this.showMessage = "Please entry valid email";
      this.email.nativeElement.focus();
      return true;
    }
    else if (this.registerForm.value.aboutUser === '') {
      this.showMessage = "Please enter about user ";
      this.aboutUser.nativeElement.focus();
      return true;
    }
    else if (this.registerForm.value.message === '') {
      this.showMessage = "Please enter message";
      this.message.nativeElement.focus();
      return true;
    }

    return false;
  }

}
