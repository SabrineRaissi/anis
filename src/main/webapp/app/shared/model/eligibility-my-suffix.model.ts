import { IPossibleValueMySuffix } from 'app/shared/model/possible-value-my-suffix.model';

export interface IEligibilityMySuffix {
    id?: number;
    criteria?: string;
    score?: number;
    etablissementId?: number;
    possibleValues?: IPossibleValueMySuffix[];
    theseId?: number;
}

export class EligibilityMySuffix implements IEligibilityMySuffix {
    constructor(
        public id?: number,
        public criteria?: string,
        public score?: number,
        public etablissementId?: number,
        public possibleValues?: IPossibleValueMySuffix[],
        public theseId?: number
    ) {}
}
