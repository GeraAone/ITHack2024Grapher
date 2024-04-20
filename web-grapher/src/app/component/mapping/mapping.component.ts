import { Component } from '@angular/core';
import {ImageGraphMappingComponent} from "../image-graph-mapping/image-graph-mapping.component";
import {GraphDto} from "../../store/dto/graph.dto";
import {ImagePropertiesModel} from "../../store/model/image-properties.model";
import {NodeDto} from "../../store/dto/node.dto";
import {LinkDto} from "../../store/dto/link.dto";


@Component({
  selector: 'app-mapping',
  standalone: true,
  imports: [
    ImageGraphMappingComponent
  ],
  templateUrl: './mapping.component.html',
  styleUrl: './mapping.component.scss'
})
export class MappingComponent {

  graph: GraphDto = this.createGraph();
  imageProperties = new ImagePropertiesModel(450*1.5, 450*1.5);

  private createGraph(): GraphDto {
    // Создаем узлы
    const node1 = new NodeDto('node1', 100, 100);
    const node2 = new NodeDto('node2', 200, 200);
    const node3 = new NodeDto('node3', 300, 300);

    // Создаем связи
    const link1 = new LinkDto(node1, node2);
    const link2 = new LinkDto(node2, node3);

    // Создаем граф
    const graph = new GraphDto('Example Graph', [node1, node2, node3], [link1, link2]);
    return graph;
  }

}
