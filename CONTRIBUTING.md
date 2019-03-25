# Contributing

All we love is only pull request so we have some rules used to make your PR looks good for reviewers.

> Note that you should file a new issue in our jira board to discuss the PR detail before submitting a PR.

## Quick start

- Fork and clone the repo
- Install dependencies. See our [README.md](README.md) for development machine setup
- Create a branch with your PR with `git checkout -b ${your-branch-name}`
- Push your PR to remote: `git push origin ${your-branch-name}`
- Create the PR with GitHub web UI and wait for reviews

## A pull request must:

#### Pass all tests

- Your PR should not make ohara unstable, if it does. It should be reverted ASAP.
- You can either run these tests on your local (see our [README.md](README.md) for more info on how to run tests) or by opening the PR on our repo. These tests will be running on your CI server.

#### Pass code style check

#### Address all reviewers' comments

## A pull request should:

#### Be as small in scope as possible.

Large PR is often hard to review.

#### Add new tests

## A pull request should not:

#### Bring in new libraries (or updating libraries to new version) without prior discussion

Do not update the dependencies unless you have a good reason.

#### Bring in new module without prior discussion

#### Bring in new APIs for Configurator without prior discussion
