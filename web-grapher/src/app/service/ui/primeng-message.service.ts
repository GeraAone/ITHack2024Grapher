import { Injectable } from '@angular/core';
import {MessageService} from "primeng/api";


@Injectable({
  providedIn: 'root'
})
export class PrimengMessageService {

  private _life: number = 5000; // in ms

  constructor(private messageService: MessageService) { }

  private getCapitalized(sourceString: string): string {
    return sourceString[0].toUpperCase() + sourceString.substring(1, sourceString.length);
  }

  private showMessage(severity: MessageTypeEnum, detail: string, sticky: boolean): void {
    this.messageService.add({
      key: MessagePosition.TOP_RIGHT,
      severity: severity,
      summary: this.getCapitalized(severity),
      detail: detail,
      closable: true,
      life: this._life,
      sticky: sticky
    });
  }

  showSuccessMessage(detailContent: string): void {
    this.showMessage(MessageTypeEnum.SUCCESS, detailContent, false);
  }

  showInfoMessage(detailContent: string): void {
    this.showMessage(MessageTypeEnum.INFO, detailContent, false);
  }

  showWarningMessage(detailContent: string): void {
    this.showMessage(MessageTypeEnum.WARN, detailContent, false);
  }

  showErrorMessage(detailContent: string): void {
    this.showMessage(MessageTypeEnum.ERROR, detailContent, true);
  }

  showMessageDependsHttpStatusCode(httpStatusCode: number): void {
    if (httpStatusCode.toString().startsWith('1')) {
      this.showInfoMessage('Informational response!');
    } else if (httpStatusCode.toString().startsWith('2')) {
      this.showSuccessMessage('Successful response!');
    } else if (httpStatusCode.toString().startsWith('3')) {
      this.showInfoMessage('Redirection message!');
    } else if (httpStatusCode.toString().startsWith('4')) {
      this.showErrorMessage('Client error response!');
    } else if (httpStatusCode.toString().startsWith('4')) {
      this.showErrorMessage('Server error response!');
    } else {
      this.showErrorMessage('Unknown error!');
    }
  }

}

enum MessageTypeEnum {
  SUCCESS = 'success',
  INFO = 'info',
  WARN = 'warn',
  ERROR = 'error'
}

enum MessagePosition {
  TOP_LEFT = 'tl',
  TOP_CENTER = 'tc',
  TOP_RIGHT = 'tr',
  BOTTOM_LEFT = 'bl',
  BOTTOM_CENTER = 'bc',
  BOTTOM_RIGHT = 'br',
}

