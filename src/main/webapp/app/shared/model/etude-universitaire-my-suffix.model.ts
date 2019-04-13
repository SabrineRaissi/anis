export interface IEtudeUniversitaireMySuffix {
    id?: number;
    anneeUniversitaire?: string;
    etablissement?: string;
    diplome?: string;
    niveauEtude?: string;
    remarque?: string;
    doctorantId?: number;
}

export class EtudeUniversitaireMySuffix implements IEtudeUniversitaireMySuffix {
    constructor(
        public id?: number,
        public anneeUniversitaire?: string,
        public etablissement?: string,
        public diplome?: string,
        public niveauEtude?: string,
        public remarque?: string,
        public doctorantId?: number
    ) {}
}
