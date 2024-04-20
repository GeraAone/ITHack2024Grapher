import {SimulationLinkDatum} from "d3";
import {NodeDto} from "./node.dto";


export class LinkDto implements SimulationLinkDatum<NodeDto> {

  // Must - defining enforced implementation properties
  source: NodeDto;
  target: NodeDto;

  // Optional - defining optional implementation properties - required for relevant typing assistance
  index?: number;

  constructor(source: NodeDto,
              target: NodeDto) {
    this.source = source;
    this.target = target;
  }
}
