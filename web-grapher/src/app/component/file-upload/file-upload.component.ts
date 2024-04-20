import {Component} from '@angular/core';
import {PrimengMessageService} from "../../service/ui/primeng-message.service";
import {FileSelectEvent, FileUploadEvent, FileUploadModule} from "primeng/fileupload";
import {NgForOf, NgIf} from "@angular/common";
import {Router} from "@angular/router";


@Component({
  selector: 'app-file-upload',
  standalone: true,
  imports: [
    FileUploadModule,
    NgIf,
    NgForOf
  ],
  templateUrl: './file-upload.component.html',
  styleUrl: './file-upload.component.scss'
})
export class FileUploadComponent {

  selectedFile: File;

  constructor(private primengMessageService: PrimengMessageService) { }

  select(event: FileSelectEvent): void {
    this.selectedFile = event.currentFiles[0];
    console.log(this.selectedFile);
    this.primengMessageService.showInfoMessage('File Uploaded');
  }
}
