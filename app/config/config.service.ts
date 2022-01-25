import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ConfigService {
  constructor(private http: HttpClient) { }
}

export const baseURL: string = "http://ec2-54-159-181-142.compute-1.amazonaws.com/"
