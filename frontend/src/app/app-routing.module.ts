import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BodyComponent } from './component/body/body.component';
import { HeaderComponent } from './component/header/header.component';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { IndexComponent } from './index/index.component';
import { BusinessforsaleComponent } from './component/businessforsale/businessforsale.component';
import { FranchiseBusinessComponent } from './component/franchise-business/franchise-business.component';
import { InvestmentORBuyersComponent } from './component/investment-orbuyers/investment-orbuyers.component';
import { BrokerComponent } from './component/broker/broker.component';
import { BusinessServicesComponent } from './component/business-services/business-services.component';
import { BlogComponent } from './component/blog/blog.component';
import { AboutUsComponent } from './component/about-us/about-us.component';
import { ContactComponent } from './component/contact/contact.component';
import { BusinessValuationComponent } from './component/business-valuation/business-valuation.component';
import { BusinessSetupComponent } from './component/business-setup/business-setup.component';
import { BusinessAdvisoryComponent } from './component/business-advisory/business-advisory.component';
import { BusinessListingDetailsComponent } from './component/business-listing-details/business-listing-details.component';
import { AdvertListingDetailsComponent } from './component/advert-listing-details/advert-listing-details.component';
import { AddBusinessListingComponent } from './component/admin/add-business-listing/add-business-listing.component';
import { HomeComponent } from './component/admin/home/home.component';
import { BusinessServiceDetailsComponent } from './component/business-service-details/business-service-details.component';
import { UserHomeComponent } from './component/user/user-home/user-home.component';
import { FaqComponent } from './component/faq/faq.component';
import { PrivacyPolicyComponent } from './component/privacy-policy/privacy-policy.component';
import { TermConditionComponent } from './component/term-condition/term-condition.component';
import { TestmonalComponent } from './component/testmonal/testmonal.component';
import { TestmonalFormComponent } from './component/testmonal-form/testmonal-form.component';
import { MyListingComponent } from './component/my-listing/my-listing.component';
import { MyDashboardComponent } from './component/my-dashboard/my-dashboard.component';
import { MyPackageComponent } from './component/my-package/my-package.component';
import { ContactBusinessComponent } from './component/contact-business/contact-business.component';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import { MyAdvertsComponent } from './component/my-adverts/my-adverts.component';
import { ChangePasswordComponent } from './component/change-password/change-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { BlogInsidePageComponent } from './component/blog-inside-page/blog-inside-page.component';
import { HowitworksComponent } from './component/howitworks/howitworks.component';
import { CreateAdvertComponent } from './component/create-advert/create-advert.component';

const routes: Routes = [
  {path: '', component: IndexComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'blog-gride', component: BlogComponent},
  {path: 'about-us', component: AboutUsComponent},
  {path: 'contact-us', component: ContactComponent},
  {path: 'faq', component: FaqComponent},
  {path: 'privacy-policy', component: PrivacyPolicyComponent},
  {path: 'term-condition', component: TermConditionComponent},
  {path: 'testmonal', component: TestmonalComponent},
  {path: 'testmonal-form', component: TestmonalFormComponent},  
  {path: 'buy-a-business/:listingtype', component: BusinessforsaleComponent},
  {path: 'Franchisee_Opportunities', component: FranchiseBusinessComponent},
  {path: 'sell-a-business/buyers-or-invstors', component: InvestmentORBuyersComponent},
  {path: 'sell-a-business/brokers', component: BrokerComponent},
  {path: 'sell-a-business/business-services', component: BusinessServicesComponent},
  {path: 'sell-a-business/business-service/:service_id', component: BusinessServiceDetailsComponent},  
  {path: 'business-advisory', component: BusinessAdvisoryComponent},
  {path: 'business-setup', component: BusinessSetupComponent},
  {path: 'business-valuation', component: BusinessValuationComponent},
  {path: 'buy-a-business/:listingtype/:listing_id', component: BusinessListingDetailsComponent},
  {path: 'sell-a-business/:listingtype/:listing_id', component: AdvertListingDetailsComponent},  
  {path: 'user/add-listing',component:AddBusinessListingComponent},
  {path:'admin/home',component:MyDashboardComponent} ,
  {path:'user/home',component:MyDashboardComponent},
  {path:'user/my-listings',component:MyListingComponent},
  {path:'business/contact/:listing_type/:listing_id',component:ContactBusinessComponent},
  {path:'user/my-dashboard',component:MyDashboardComponent},
  {path:'user/my-adverts',component:MyAdvertsComponent},
  {path:'user/my-package',component:MyPackageComponent},
  {path:'forgot-password',component:ForgotPasswordComponent},
  {path:'reset-password/:token',component:ResetPasswordComponent},
  {path:'blog/blog-inside-page',component:BlogInsidePageComponent},
  {path:'how-it-works',component:HowitworksComponent},
  {path:'user/create-adverts',component:CreateAdvertComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
