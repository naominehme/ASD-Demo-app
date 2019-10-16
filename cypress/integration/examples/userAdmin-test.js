context('User Admin function test', () => {
    beforeEach(() => {
        cy.visit('https://asd-demo-app-naomi.herokuapp.com/staffPortal.html')
       
    })


        describe('show user detail page', () => {
            it('Available function', () => {

                cy.contains('User')
            })
            it('show user detail page', () => {
                cy.get('button[id=udm]').click();

                cy.contains('username')
            })
        })

})



