context('Actions', () => {
    beforeEach(() => {
        cy.visit('http://localhost:63342/ASD-Demo-app/src/main/webapp/login.html?_ijt=diu80sgve4s7bea08ld6ep5ivf')
    })

    it('incorrect details', () => {
        const username = 'test123';
        const password = '123';
        cy.get('input[id=username]').type(username);
        cy.get('input[id=password]').type(password);
        cy.get('button[id=login]').click();
        cy.contains('USER NOT FOUND');
    })
})