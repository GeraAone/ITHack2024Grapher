import {Component, OnInit, ViewChild} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {NodeDto} from "./store/dto/node.dto";
import {LinkDto} from "./store/dto/link.dto";
import {GraphDto} from "./store/dto/graph.dto";
import {GraphComponent} from "./component/graph/graph.component";
import {NgOptimizedImage} from "@angular/common";
import {GraphComponentModeEnum} from "./store/enum/graph-component-mode.enum";
import {GraphContainerPropertiesModel} from "./store/model/graph-container-properties.model";
import {NodePropertiesModel} from "./store/model/node-properties.model";
import {ToolbarComponent} from "./component/toolbar/toolbar.component";
import {ToastModule} from "primeng/toast";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    GraphComponent,
    NgOptimizedImage,
    ToolbarComponent,
    ToastModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
