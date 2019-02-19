import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {GlobalMaskService} from './services/global-mask.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ToastComponent} from './layout/toast/toast.component';
import {ToasterModule, ToasterService} from 'angular2-toaster';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HomeComponent} from "./home/home.component";
import {HttpClientModule} from "@angular/common/http";
import {MachineService} from "./services/machine.service";
import {AlertDialogComponent} from './alert-dialog/alert-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ToastComponent,
    AlertDialogComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgbModule,
    AppRoutingModule,
    ReactiveFormsModule,
    ToasterModule.forRoot()
  ],
  providers: [
    GlobalMaskService,
    ToasterService,
    MachineService
  ],
  entryComponents: [AlertDialogComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
}
