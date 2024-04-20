import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {Path} from "d3";
import {GraphDto} from "../../store/dto/graph.dto";
import {GraphComponent} from "../graph/graph.component";
import {GraphComponentModeEnum} from "../../store/enum/graph-component-mode.enum";
import {NgOptimizedImage} from "@angular/common";
import {ImagePropertiesModel} from "../../store/model/image-properties.model";
import {GraphContainerPropertiesModel} from "../../store/model/graph-container-properties.model";
import {NodeDto} from "../../store/dto/node.dto";
import {LinkDto} from "../../store/dto/link.dto";


@Component({
  selector: 'app-image-graph-mapping',
  standalone: true,
  imports: [
    GraphComponent,
    NgOptimizedImage
  ],
  templateUrl: './image-graph-mapping.component.html',
  styleUrl: './image-graph-mapping.component.scss'
})
export class ImageGraphMappingComponent implements OnInit {

  protected readonly GraphComponentModeEnum = GraphComponentModeEnum;

  @ViewChild(GraphComponent) graphComponent: GraphComponent;

  /**
   * Getting from path <assets/source-graph-images/...>.
   */
  @Input() sourceImageUrl: Path;

  /**
   * A graph DTO projection which processing.
   */
  @Input() processingGraph: GraphDto;
  graphProperties: GraphContainerPropertiesModel;

  @Input() imageProperties: ImagePropertiesModel;


  ngOnInit(): void {
    this.graphProperties = new GraphContainerPropertiesModel(this.imageProperties.width, this.imageProperties.height);
  }

  addNode(mouseEvent: MouseEvent): void {
    const freshNode: NodeDto = new NodeDto('123', mouseEvent.clientX, mouseEvent.clientY);
    const freshNode2: NodeDto = new NodeDto('124', mouseEvent.clientX + 100, mouseEvent.clientY + 150);
    const freshLink: LinkDto = new LinkDto(freshNode, freshNode2);
    this.graphComponent.addNode(freshNode, []);
    this.graphComponent.addNode(freshNode2, [freshLink]);
  }

  chooseNode(clickedNode: NodeDto): void {
    console.log('clicked node from mapping');
  }

  chooseLink(clickedLink: LinkDto): void {
    console.log('clicked link from mapping');
  }
}
