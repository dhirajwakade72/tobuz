import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'firstName'
})
export class FirstNamePipe implements PipeTransform {

  transform(value: string): string {
    return value.split(' ')[0]; // Splits the string and returns the first element
  }

}
