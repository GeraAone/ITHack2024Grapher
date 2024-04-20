import { Component } from '@angular/core';
import {ToolbarModule} from "primeng/toolbar";
import {ButtonModule} from "primeng/button";
import {TooltipModule} from "primeng/tooltip";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {ActivatedRoute, Router} from "@angular/router";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService} from "primeng/api";
import {FileUploadComponent} from "../file-upload/file-upload.component";


@Component({
  selector: 'app-toolbar',
  standalone: true,
  imports: [
    ToolbarModule,
    ButtonModule,
    TooltipModule,
    ConfirmDialogModule
  ],
  templateUrl: './toolbar.component.html',
  styleUrl: './toolbar.component.scss'
})
export class ToolbarComponent {

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private dialogService: DialogService,
              private confirmationService: ConfirmationService) {
  }

  async startMapping(): Promise<void> {
    // this.openFileUploadDialog();
    await this.router.navigate(['grapher', 'mapping']);
  }

  private openFileUploadDialog(): void {
    const fileUploadDialog: DynamicDialogRef = this.dialogService.open(FileUploadComponent, {
      header: 'File upload menu',
      draggable: true,
      modal: true
    });

    fileUploadDialog.onClose.subscribe(() => {
      this.confirmationService.confirm({
        key: 'confirm-saving-images',
        accept: () => window.location.reload()
      });
    })
  }

  async openStandardImagesTable(): Promise<void> {
    await this.router.navigate(['grapher', 'table']);
  }

  async openHelpPage(): Promise<void> {
    await this.router.navigate(['grapher', 'help']);
  }
}
