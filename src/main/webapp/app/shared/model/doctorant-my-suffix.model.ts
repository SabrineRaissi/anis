import { Moment } from 'moment';

export interface IDoctorantMySuffix {
    id?: number;
    nationalite?: string;
    dateNissance?: Moment;
    sexe?: string;
    etatCivil?: string;
    adresse?: string;
    profession?: string;
    employeur?: string;
    profilePicContentType?: string;
    profilePic?: any;
}

export class DoctorantMySuffix implements IDoctorantMySuffix {
    constructor(
        public id?: number,
        public nationalite?: string,
        public dateNissance?: Moment,
        public sexe?: string,
        public etatCivil?: string,
        public adresse?: string,
        public profession?: string,
        public employeur?: string,
        public profilePicContentType?: string,
        public profilePic?: any
    ) {}
}
