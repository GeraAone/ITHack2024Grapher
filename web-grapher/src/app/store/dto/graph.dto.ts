import {NodeDto} from "./node.dto";
import {LinkDto} from "./link.dto";


export class GraphDto {

  name: string;
  nodes: NodeDto[] = [];
  links: LinkDto[] = [];

  constructor(name: string, nodes: NodeDto[], links: LinkDto[]) {
    this.nodes = nodes;
    this.links = links;
  }

}
