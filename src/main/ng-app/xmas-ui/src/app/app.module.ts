import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { WishesComponent } from './wishes/wishes.component';
import { DealsComponent } from './deals/deals.component';
import { DonateComponent } from './donate/donate.component';
import { TestimonialComponent } from './testimonial/testimonial.component';
import { ContactComponent } from './contact/contact.component';
import { SubscribeComponent } from './subscribe/subscribe.component';
import { FooterComponent } from './footer/footer.component';
import { UserComponent } from './user/user.component';
import { HttpClientModule } from '@angular/common/http';
import { SignupComponent } from './signup/signup.component';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PollsComponent } from './polls/polls.component';
import { ResultsComponent } from './results/results.component';
import { TimerComponent } from './timer/timer.component';
import { RootComponent } from './root/root.component';
import { RouterModule } from '@angular/router';

import { NgChartsModule } from 'ng2-charts';
import { CertificateComponent } from './certificate/certificate.component';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    WishesComponent,
    DealsComponent,
    DonateComponent,
    TestimonialComponent,
    ContactComponent,
    SubscribeComponent,
    FooterComponent,
    UserComponent,
    
    SignupComponent,
         PollsComponent,
         ResultsComponent,
         TimerComponent,
         RootComponent,
         CertificateComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgChartsModule,
    RouterModule.forRoot([
      { path: 'results', component: ResultsComponent }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
