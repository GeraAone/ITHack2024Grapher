import { Injectable } from '@angular/core';
import {NodeDto} from "../../store/dto/node.dto";
import {LinkDto} from "../../store/dto/link.dto";


@Injectable({
  providedIn: 'root'
})
export class GraphVisualService {

  constructor() { }

  // Метод для отрисовки связей графа
  addLinks(svg: any, links: LinkDto[]): void {
    svg.append('g')
      .selectAll('line')
      .data(links)
      .enter()
      .append('line')
      .attr('stroke', 'black')
      .attr('stroke-width', 5)
      .attr('id', (link: LinkDto) => link.index);
  }

  // Метод для отрисовки узлов графа
  addNodes(svg: any, nodes: NodeDto[]): any {
    return svg.append('g')
      .selectAll('circle')
      .data(nodes)
      .enter()
      .append('circle')
      .attr('r', 10)
      .attr('fill', 'steelblue')
      .attr('id', (node: NodeDto) => node.id);
  }

}
