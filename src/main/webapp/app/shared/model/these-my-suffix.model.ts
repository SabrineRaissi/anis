import { IEligibilityMySuffix } from 'app/shared/model/eligibility-my-suffix.model';

export interface ITheseMySuffix {
    id?: number;
    designation?: string;
    etablissementId?: number;
    eligibilites?: IEligibilityMySuffix[];
}

export class TheseMySuffix implements ITheseMySuffix {
    constructor(
        public id?: number,
        public designation?: string,
        public etablissementId?: number,
        public eligibilites?: IEligibilityMySuffix[]
    ) {}
}
