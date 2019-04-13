export interface ILastThesesMySuffix {
    id?: number;
    anneeUniversitaire?: string;
    etablissement?: string;
    sujetThese?: string;
    doctorantId?: number;
}

export class LastThesesMySuffix implements ILastThesesMySuffix {
    constructor(
        public id?: number,
        public anneeUniversitaire?: string,
        public etablissement?: string,
        public sujetThese?: string,
        public doctorantId?: number
    ) {}
}
