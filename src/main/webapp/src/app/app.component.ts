import {Component} from '@angular/core';
import {GlobalMaskService} from './services/global-mask.service';
import {ToasterConfig} from 'angular2-toaster';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  title = 'NCR connections';
  toasterConfig: ToasterConfig;

  constructor(private maskService: GlobalMaskService) {

    this.toasterConfig = new ToasterConfig({
        showCloseButton: false,
        tapToDismiss: true,
        animation: 'fade',
        timeout: 2000
      });

    if (!this.maskService.isMaskHidden()) {
      setTimeout(() => {
        this.maskService.hideMask();
      }, 1000);
    }
  }

}
