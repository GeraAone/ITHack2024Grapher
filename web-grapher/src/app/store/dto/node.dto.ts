import {SimulationNodeDatum} from "d3";


export class NodeDto implements SimulationNodeDatum {

  id: string;
  x: number;
  y: number;

  // Optional - defining optional implementation properties - required for relevant typing assistance
  index?: number;
  vx?: number;
  vy?: number;
  fx?: number | null;
  fy?: number | null;

  constructor(id: string, x: number, y: number) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.fx = x;
    this.fy = y;
  }
}
