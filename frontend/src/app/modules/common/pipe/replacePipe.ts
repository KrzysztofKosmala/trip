import { PipeTransform } from "@angular/core";
import { serialize } from "v8";

export class ReplacePipe implements PipeTransform
{
    transform(value: string, strToReplace: string, replacementStr: string) {
        if(!value || !strToReplace || !replacementStr)
        {
            return value;
        }
        return value.replace(new RegExp(strToReplace, 'g'), replacementStr)
    }

}