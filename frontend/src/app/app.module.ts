import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './component/header/header.component';
import { BodyComponent } from './component/body/body.component';
import { FooterComponent } from './component/footer/footer.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { IndexComponent } from './index/index.component';
import { FiltersComponent } from './component/filters/filters.component';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { BusinessforsaleComponent } from './component/businessforsale/businessforsale.component';
import { HttpClientModule } from '@angular/common/http';

import { FranchiseBusinessComponent } from './component/franchise-business/franchise-business.component';
import { InvestmentORBuyersComponent } from './component/investment-orbuyers/investment-orbuyers.component';
import { BrokerComponent } from './component/broker/broker.component';
import { BusinessServicesComponent } from './component/business-services/business-services.component';
import { BlogComponent } from './component/blog/blog.component';
import { AboutUsComponent } from './component/about-us/about-us.component';
import { ContactComponent } from './component/contact/contact.component';
import { BusinessAdvisoryComponent } from './component/business-advisory/business-advisory.component';
import { BusinessSetupComponent } from './component/business-setup/business-setup.component';
import { BusinessValuationComponent } from './component/business-valuation/business-valuation.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BusinessListingDetailsComponent } from './component/business-listing-details/business-listing-details.component';
import { AdvertListingDetailsComponent } from './component/advert-listing-details/advert-listing-details.component';
import { FormsModule, NgModel, NgSelectOption } from '@angular/forms';
import { BusinessServiceDetailsComponent } from './component/business-service-details/business-service-details.component';
import { HomeComponent } from './component/admin/home/home.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AddBusinessListingComponent } from './component/admin/add-business-listing/add-business-listing.component';
import { UserHomeComponent } from './component/user/user-home/user-home.component';
import { UserfiltersComponent } from './component/userfilters/userfilters.component';
import { AlphabetDirective } from './directive/alphabet.directive';
import { NumericOnlyDirectiveDirective } from './directive/numeric-only-directive.directive';
import { EmailValidatorDirectiveDirective } from './directive/email-validator-directive.directive';
import { OverAllBusinessComponent } from './component/over-all-business/over-all-business.component';
import { FaqComponent } from './component/faq/faq.component';
import { PrivacyPolicyComponent } from './component/privacy-policy/privacy-policy.component';
import { TermConditionComponent } from './component/term-condition/term-condition.component';
import { TestmonalComponent } from './component/testmonal/testmonal.component';
import { TestmonalFormComponent } from './component/testmonal-form/testmonal-form.component';
import { LikeDislikeComponent } from './component/like-dislike/like-dislike.component';
import { MyListingComponent } from './component/my-listing/my-listing.component';
import { MyPackageComponent } from './component/my-package/my-package.component';
import { MyDetailsComponent } from './component/my-details/my-details.component';
import { MyDashboardComponent } from './component/my-dashboard/my-dashboard.component';
import { ContactBusinessComponent } from './component/contact-business/contact-business.component';
import { PadEndPipe } from './pad-end.pipe';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import { ChangePasswordComponent } from './component/change-password/change-password.component';
import { MyAdvertsComponent } from './component/my-adverts/my-adverts.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { BlogInsidePageComponent } from './component/blog-inside-page/blog-inside-page.component';
import { CapitalizeFirstPipe } from './pipe/capitalize-first.pipe';
import { FirstNamePipe } from './pipe/first-name.pipe';
import { HowitworksComponent } from './component/howitworks/howitworks.component';
import { CommanContactsComponent } from './component/comman-contacts/comman-contacts.component';
import { CreateAdvertComponent } from './component/create-advert/create-advert.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    BodyComponent,
    FooterComponent,
    IndexComponent,
    FiltersComponent,
    LoginComponent,
    RegisterComponent,
    BusinessforsaleComponent,
    FranchiseBusinessComponent,
    InvestmentORBuyersComponent,
    BrokerComponent,
    BusinessServicesComponent,
    BlogComponent,
    AboutUsComponent,
    ContactComponent,
    BusinessAdvisoryComponent,
    BusinessSetupComponent,
    BusinessValuationComponent,
    BusinessListingDetailsComponent,
    AdvertListingDetailsComponent,
    BusinessServiceDetailsComponent,
    HomeComponent,
    AddBusinessListingComponent,
    UserfiltersComponent,
    UserHomeComponent,
    UserfiltersComponent,
    AlphabetDirective,
    NumericOnlyDirectiveDirective,
    EmailValidatorDirectiveDirective,
    OverAllBusinessComponent,
    FaqComponent,
    PrivacyPolicyComponent,
    TermConditionComponent,
    TestmonalComponent,
    TestmonalFormComponent,
    LikeDislikeComponent,
    MyListingComponent,
    MyPackageComponent,
    MyDetailsComponent,
    MyDashboardComponent,
    ContactBusinessComponent,
    PadEndPipe,
    ForgotPasswordComponent,
    ChangePasswordComponent,
    MyAdvertsComponent,
    ResetPasswordComponent,
    BlogInsidePageComponent,
    CapitalizeFirstPipe,
    FirstNamePipe,
    HowitworksComponent,
    CommanContactsComponent,
    CreateAdvertComponent    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    BrowserAnimationsModule  ,
    FormsModule,
    ReactiveFormsModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
