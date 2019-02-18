import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GlobalMaskService {

  private isHidden = false;

  constructor() {
  }

  isMaskHidden(): boolean {
    return this.isHidden;
  }

  hideMask(): void {
    this._toggleMask(false);
  }

  showMask(): void {
    this._toggleMask(true);
  }

  private _toggleMask(show: boolean): void {

    const mask = document.querySelector('.global-mask');
    if (mask) {
      const style = show ? 'display: block' : 'display: none';
      mask.setAttribute('style', style);
    }
  }
}
