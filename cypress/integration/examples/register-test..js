context('Register test', () => {
    beforeEach(() => {
        cy.visit('https://asd-demo-app-249308.appspot.com/Register.html')
    })


    describe('Email', () => {
        it('should show invalid email', () => {
            cy.get('input[name=email]')
                .type('abc')
                .blur()

            cy.get('[data-testid=err-email]')
                .should('exist')
                .and('contain', 'Invalid Email')
        })
    })
})