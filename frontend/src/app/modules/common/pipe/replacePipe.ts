import { PipeTransform } from "@angular/core";

export class ReplacePipe implements PipeTransform
{
    transform(value: string, strToReplace: string, replacementStr: string) {
        throw new Error("Method not implemented.");
    }

}