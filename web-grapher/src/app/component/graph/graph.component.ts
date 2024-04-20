import {Component, ElementRef, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {GraphDto} from "../../store/dto/graph.dto";
import {NodeDto} from "../../store/dto/node.dto";
import {LinkDto} from "../../store/dto/link.dto";
import {select} from "d3";
import {GeometryElementEnum} from "../../store/enum/geometry-element.enum";
import {GraphComponentModeEnum} from "../../store/enum/graph-component-mode.enum";
import {GraphContainerPropertiesModel} from "../../store/model/graph-container-properties.model";
import {NodePropertiesModel} from "../../store/model/node-properties.model";
import {LinkPropertiesModel} from "../../store/model/link-properties.model";


@Component({
  selector: 'app-graph',
  standalone: true,
  imports: [],
  template: '<div></div>',
  styleUrl: './graph.component.scss'
})
/**
 * TODO: add custom styles for graph (add styles in GraphPropertiesModel).
 */
export class GraphComponent implements OnInit {

  protected svgElementContainer: any;

  @Input() graph: GraphDto;
  @Input() mode: GraphComponentModeEnum = GraphComponentModeEnum.READONLY;

  @Input() graphContainerProperties: GraphContainerPropertiesModel = new GraphContainerPropertiesModel(600, 300);
  @Input() nodeProperties: NodePropertiesModel = new NodePropertiesModel(10, 'steelblue');
  @Input() linkProperties: LinkPropertiesModel = new LinkPropertiesModel('black', 5);

  @Output() onFocusNode: EventEmitter<NodeDto> = new EventEmitter<NodeDto>();
  @Output() onClickNode: EventEmitter<NodeDto> = new EventEmitter<NodeDto>();
  @Output() onBlurNode: EventEmitter<NodeDto> = new EventEmitter<NodeDto>();

  @Output() onFocusLink: EventEmitter<LinkDto> = new EventEmitter<LinkDto>();
  @Output() onClickLink: EventEmitter<LinkDto> = new EventEmitter<LinkDto>();
  @Output() onBlurLink: EventEmitter<LinkDto> = new EventEmitter<LinkDto>();

  constructor(private elementRef: ElementRef) { }

  ngOnInit(): void {
    const htmlDivElement = this.elementRef.nativeElement.querySelector('div');
    this.createRootSvgElementReference(htmlDivElement);
    this.visualizeGraph();
  }

  /**
   * Creates a root SVG element for containing a graph.
   * @param element is root element container in HTML (by default, is div tag).
   */
  protected createRootSvgElementReference(element: HTMLElement): void {
    this.svgElementContainer = select(element)
      .append('svg')
      .attr('width', this.graphContainerProperties.widthPx)
      .attr('height', this.graphContainerProperties.heightPx);
  }

  protected addLinks(links: LinkDto[]): void {
    this.svgElementContainer
      .append('g')
      .selectAll(GeometryElementEnum.LINE)
      .data(links)
      .enter()
      .append(GeometryElementEnum.LINE)
      .attr('id', (link: LinkDto) => link.index)
      .attr('stroke', this.linkProperties.stroke)
      .attr('stroke-width', this.linkProperties.strokeWidth);
  }

  addNodes(nodes: NodeDto[]): any {
    this.svgElementContainer
      .append('g')
      .selectAll(GeometryElementEnum.CIRCLE)
      .data(nodes)
      .enter()
      .append(GeometryElementEnum.CIRCLE)
      .attr('id', (node: NodeDto) => node.id)
      .attr('r', this.nodeProperties.radius)
      .attr('fill', this.nodeProperties.fill);
  }

  visualizeGraph(): void {
    this.addLinks(this.graph.links);
    this.addNodes(this.graph.nodes);

    this.updateLinkPositions();
    this.updateNodePositions();

    this.onProcessingLink();
    this.onProcessingNode();
  }


  protected updateNodePositions(): void {
    this.svgElementContainer
      .selectAll('circle')
      .attr('cx', (node: NodeDto) => node.x)
      .attr('cy', (node: NodeDto) => node.y);
  }

  protected updateLinkPositions(): void {
    this.svgElementContainer
      .selectAll('line')
      .attr('x1', (link: LinkDto) => link.source.x)
      .attr('y1', (link: LinkDto) => link.source.y)
      .attr('x2', (link: LinkDto) => link.target.x)
      .attr('y2', (link: LinkDto) => link.target.y);
  }

  onProcessingLink(): void {
    this.svgElementContainer
      .selectAll('line')
      .on('click', (pointerEvent: PointerEvent, clickedLink: LinkDto): void => {
        this.onClickLink.emit(clickedLink);
      })
      .on('mouseover', (pointerEvent: PointerEvent, focusedLink: LinkDto): void => {
        this.focusLink(pointerEvent);
        this.onFocusLink.emit(focusedLink);
      })
      .on('mouseout', (pointerEvent: PointerEvent, blurredLink: LinkDto): void => {
        this.blurLink(pointerEvent);
        this.onBlurLink.emit(blurredLink);
      });
  }

  onProcessingNode(): void {
    this.svgElementContainer
      .selectAll('circle')
      .on('click', (pointerEvent: PointerEvent, clickedNode: NodeDto): void => {
        this.onClickNode.emit(clickedNode);
      })
      .on('mouseover', (pointerEvent: PointerEvent, focusedNode: NodeDto): void => {
        this.focusNode(pointerEvent);
        this.onFocusNode.emit(focusedNode);
      })
      .on('mouseout', (pointerEvent: PointerEvent, blurredNode: NodeDto): void => {
        this.blurNode(pointerEvent);
        this.onBlurNode.emit(blurredNode);
      });
  }

  addNode(node: NodeDto, links: LinkDto[]): void {
    if (this.mode === GraphComponentModeEnum.VARIABLE) {
      this.graph.nodes.push(node);
      this.graph.links.push(...links);
      this.visualizeGraph();
    }
  }

  protected focusNode(pointerEvent: PointerEvent): void {
    select(pointerEvent.target as HTMLElement)
      .attr('r', this.nodeProperties.radius + 0.33 * this.nodeProperties.radius);
  }

  protected blurNode(pointerEvent: PointerEvent): void {
    select(pointerEvent.target as HTMLElement)
      .attr('r', this.nodeProperties.radius);
  }

  protected focusLink(pointerEvent: PointerEvent): void {
    select(pointerEvent.target as HTMLElement)
      .attr('stroke-width', this.linkProperties.strokeWidth! + 0.33 * this.linkProperties.strokeWidth!);
  }

  protected blurLink(pointerEvent: PointerEvent): void {
    select(pointerEvent.target as HTMLElement)
      .attr('stroke-width', this.linkProperties.strokeWidth!);
  }

}
